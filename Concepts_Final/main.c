#include <stdio.h>
#include <stdlib.h>

// Ross Tebbetts
// Concepts of Programming language final
// this was harder than I thought
// thanks for the knowledge Dr. Wagner

typedef struct Node node; //unused type def. used for testing

struct Node //create node structure
{
    struct Node *next; //pointer to next node
    struct Node *back; //pointer to previous node
    int data; //data each node contains
};

//this function counts the length of the List by number of nodes
int nodeSize(struct Node *list)
{
    if(list!=NULL)
    {
        int count = 1; //count starts at one because the method starts from the first node
                        //and assumes the list has at least one element
        struct Node *temp = list; //create temporary list that is equal to the list passed to the method
        while(temp->next)   //while temp has a next element
        {
            count++;
            temp = temp->next; //advances current node to the node it is pointing to
        }
        return count; // returns how many times it was possible to advance a node
    }
    else
    {
        printf("list does not exist");
        return 0;
    }
}

//function to print out linked list from specific index until end.
void printList(struct Node *start)
{
    int i = 1; // variable use in print statement to show current stop.
    struct Node *temp = start; //begins temp list with first node to print out
    while(temp->next) // while a next node exists
    {
        printf("data in node at position %d: %d \n",i, temp->data); //say data in current stop
        temp = temp->next; //advance list
        i++;
    }
    printf("data in node at position %d: %d \n",i, temp->data);//print final stop
}

void printDetail(struct Node *start)
{
    int i = 1; // variable use in print statement to show current stop.
    struct Node *temp = start; //begins temp list with first node to print out
    while(temp->next) // while a next node exists
    {
        printf("data in node at position %d: %d \n",i, temp->data); //say data in current stop
        printf("Memory location of pointer at position %d: %p \n",i, temp);
        printf("Memory location of where the pointer is pointing to at position %d: %p \n",i, *temp);
        temp = temp->next; //advance list
        i++;
    }
    printf("data in node at position %d: %d \n",i, temp->data);//print final stop
}

void AddLast(struct Node *head, int datas)
{
    struct Node *last;
    last = malloc(sizeof(struct Node));//allocated memory for new node
    last->data = datas;
    struct Node *temp = head; //create temporary list that is equal to the list passed to the method
    do   //while temp has a next element
    {
        temp = temp->next; //advances current node to the node it is pointing to
    }while(temp->next);
    last->back = temp;
    temp->next = last;
    last->next = NULL;
    printf("%d is now the data in the new end node\n", datas);
}

struct Node *addHead(struct Node *head, int newData)
{
    struct Node *newHead;
    newHead = malloc(sizeof(struct Node));
    newHead->data = newData;
    newHead->next = head;
    newHead->back = NULL;
    newHead->next->back = newHead;
    return newHead;
}

// function to add a node in list
void listAdd(struct Node *head, int Nodedata, int position)
{
    printf("\nThe new node will follow node %d.\n" , position); //explains what is happening in the method
    struct Node *add; //declare node to be added
    add = malloc(sizeof(struct Node));//allocated memory for node;
    add->data = Nodedata; //place the data for the node to be added
    struct Node *temp = head;
    struct Node *hold;
    hold = malloc(sizeof(struct Node));//allocated memory for list;
    int i;
    if(position == 1)
    {
        add->next = head;
        head = add;
    }
    else
    {
        for(i=0; i<position-2; i++) //advances to node that you want the new node to come after
        {
            temp=temp->next; // advance list
        }
        hold = temp->next; // set place holder to temps next node
        temp->next = add; // set current nodes next equal to new node
        add->next = hold; // set new nodes next equal to temps original next node
        add->back = temp;//set new nodes back to the node you advanced to.
    }
}

int getNode(node *head, int position)
{
    node *temp = head;
    if(position == 1)
    {
        return head->data;
    }

    int i;

    for(i = 1;i < position;i++)
    {
        temp = temp->next;
    }
    return temp->data;
}

int listRemove(struct Node *head, int position)
{
    printf("\nThe node at position %d was removed from the list.\n", position);
    struct Node *temp = head; //set temporary node equal to head node
    struct Node *hold; //extra temporary node to hold the node just before the index to be removed
    hold = malloc(sizeof(struct Node));//allocated memory for list
    int i;
    if(position == 1)
    {
        return 1;
    }
    else
    {
        for(i=0; i<position-2; i++)// advances list to node that is to be removed. actually not entirely sure why i needs to be -2
        {                          //actually not entirely sure why i needs to be -2 but thats how it worked out after I tested my program and tweaked it.
            temp=temp->next; // advance node
        }
        hold = temp; // placeholder for node before item to be removed
        temp = temp->next; // advance to node to be removed
        hold->next = temp->next; // points node before index to be removed to the node next in line from index to be removed.
        temp->next->back = hold; // points the node ahead of the node deleted back to the node before the node being deleted.
        return 0;
    }
}

struct Node *DeepCopy(struct Node *head)// I could have shortened this by calling AddLast to populate a copy. but low coupling.
{
    struct Node *newHead;
    newHead = malloc(sizeof(struct Node));
    struct Node *copyHolder = newHead;
    struct Node *temp = head;
    int size = nodeSize(head);
    int i;
    for(i = 0; i < size; i++)
    {
        newHead->data = temp->data; // fill new node data with data from original node
        if(i != size-1) // counts through nodes to make a list of size 10
        {
            newHead->next = malloc(sizeof(struct Node));//allocated memory for next in list
            newHead->next->back = newHead;
            temp = temp->next; // advance list forward
            newHead = newHead->next;
        }
        else
        {
            newHead->next=0; // otherwise set next node to 0
        }
    }

    return copyHolder;
}

int main()
{
    int go = 999999999;
    int size = 10; //arbitrary starting list size.
    struct Node *head; //initialize head node
    head = malloc(sizeof(struct Node));//allocated memory for list
    struct Node *temp = head; // set temporary node equal to head node
    int i;
    for(i = 0; i < size; i++)
    {
        temp->data = i; // fill node data with integer i just to hand something populate the node
        if(i != size-1) // counts through nodes to make a list of size 10
        {
            temp->next = malloc(sizeof(struct Node));//allocated memory for next in list
            temp->next->back = temp;
            temp = temp->next; // advance list forward
        }
        else
        {
            temp->next=0; // otherwise set next node to 0
        }
    }

    while(go != 0)
    {
        printf("\nchoose from the following list:\n 1:Insert Node\n 2:Delete Node\n 3:New Head Node\n 4:New Tail Node\n 5:Get Node Value\n 6:Deep Copy\n 7:Print Main List\n 8:Size of Main List\n");
        scanf("%d",&go);
        int holdPosition;
        int holdData;
        if(go == 1)//insert
        {
            printf("input the position\n");
            scanf("%d",&holdPosition);
            printf("input the data you want in the node\n");
            scanf("%d",&holdData);
            if(holdPosition == 1)
            {
                head = addHead(head,holdData);
            }
            else
            {
                listAdd(head,holdData,holdPosition);
            }
            printList(head);
        }
        else if(go == 2)//delete
        {
            printf("input the position you want to delete\n");
            scanf("%d",&holdPosition);
            if(listRemove(head,holdPosition) == 1)
            {
                head = head->next;
            }
            printList(head);
        }
        else if(go == 3)//addFirst
        {
            printf("input the data you want in the first node\n");
            scanf("%d",&holdData);
            head = addHead(head,holdData);
            printList(head);
        }
        else if(go == 4)//addLast
        {
            printf("input the data you want in the last node\n");
            scanf("%d",&holdData);
            AddLast(head,holdData);
            printList(head);
        }
        else if(go == 5)//get
        {
            printf("input the position you want to get the value from\n");
            scanf("%d",&holdPosition);
            printf("%d",getNode(head,holdPosition));
        }
        else if(go == 6)//deep copy
        {
            printf("print out of Main List:\n");
            printDetail(head);
            printf("print out of Copied list\n");
            printDetail(DeepCopy(head));
        }
        else if(go == 7)//print list
        {
            printList(head);
        }
        else if(go == 8)//size
        {
            printf("%d",nodeSize(head));
        }
        else
        {
            printf("try again\n");
        }
    }
    return 0;
}




