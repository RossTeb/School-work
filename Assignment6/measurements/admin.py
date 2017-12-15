from django.contrib import admin
from .models import Area, Location, Category, Measurement


class LocationInline(admin.TabularInline):
    model = Location


class AreaAdmin(admin.ModelAdmin):
    inlines = [
        LocationInline,
    ]


class MeasurementInline(admin.TabularInline):
    model = Measurement


class LocationAdmin(admin.ModelAdmin):
    inlines = [
        MeasurementInline,
    ]


class CategoryAdmin(admin.ModelAdmin):
    filter_vertical = ("members",)


admin.site.register(Area, AreaAdmin)
admin.site.register(Location, LocationAdmin)
admin.site.register(Category, CategoryAdmin)




