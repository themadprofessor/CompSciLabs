#include <stdlib.h>
#include "queue.h"

struct q_element {
	void* data;
	int prio;
	struct q_element* next;
	struct q_element* prev;
};

struct queue {
	struct q_element* head;
	struct q_element* tail;
};


Queue *q_create(void) {
	Queue* q;
	if ((q = malloc(sizeof(Queue)))) {
		q->head = NULL;
		q->tail = NULL;
		return q;
	}
	return NULL;
}

int q_add(Queue *q, Item i, int prio) {
	struct q_element* ele;
	if (!(ele = malloc(sizeof(struct q_element)))) {
		return 0;
	}
	ele->data = i;
	ele->prio = prio;
	ele->next = NULL;
	ele->prev = NULL;

	if (!q->head) {
		q->head = ele;
		q->tail = ele;
		return 1;
	}
	if (q->tail->prio >= prio) {
		q->tail->next = ele;
		ele->prev = q->tail;
		q->tail = ele;
		return 1;
	}
	if (q->head->prio < prio) {
		ele->next = q->head;
		q->head->prev = ele;
		q->head = ele;
		return 1;
	}

	struct q_element* curr = q->head;

	while(curr->next->prio > prio) {
		curr = curr->next;
	}	

	ele->next = curr->next;
	ele->prev = curr;
	curr->next = ele;
	ele->next->prev = ele;
	return 1;
}

Item q_remove(Queue *q) {
	if (q->head) {
		struct q_element* head = q->head;
		if (head->next) {
			head->next->prev = NULL;
		}
		q->head = head->next;
		Item item = head->data;
		free(head);
		return item;
	}
	return NULL;
}
