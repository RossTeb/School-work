from datetime import datetime

from django.db import models

# Create your models here.



class Area(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=200)
    longitude = models.FloatField(default=0)
    latitude = models.FloatField(default=0)

    def __str__(self):
        return self.name

    def number_of_locations(self):
        number = 0
        for x in Location.objects.filter(area=self.id):
            number += 1
        return number

    def average_measurement(self):
        number = 0
        total = 0
        locations = self.location_set.all()
        measurements = []
        for x in locations:
            measurements += x.measurement_set.all()
        for x in measurements:
            total += x.value
            number += 1
        if total == 0:
            return None

        return total/number

    def category_names(self):
        iterations = 0
        categoryList = ''
        for x in Category.objects.filter(members__name=self.name):
            if iterations == 0:
                categoryList += x.name
                iterations += 1
            else:
                categoryList += ', ' + x.name
        return categoryList

class Location(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=200)
    altitude = models.IntegerField('altitude',default=0)
    area = models.ForeignKey('Area', on_delete=models.CASCADE)

    def __str__(self):
        return self.area.__str__() + ":" + self.name

class Measurement(models.Model):
    id = models.IntegerField(primary_key=True)
    value = models.FloatField(default=0)
    date = models.DateTimeField(default=datetime.now,blank = True)
    location = models.ForeignKey('Location', on_delete=models.CASCADE)

    def __str__(self):
        location = Location.objects.get_or_create(id=self.location.id)[0]
        return "measurement@" + location.__str__()

class Category(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=200)
    description = models.CharField(max_length=200)
    members = models.ManyToManyField(Area)

    def __str__(self):
        return self.name