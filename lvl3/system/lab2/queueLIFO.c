#include <stdlib.h>
#include "queue.h"

struct q_element {
	void* data;
	struct q_element* next;
};

struct queue {
	struct q_element* head;
};


Queue* q_create(void) {
	Queue* q;
	if ((q = malloc(sizeof(Queue)))) {
		q->head = NULL;
		return q;
	}
	return NULL;
}

int q_add(Queue* q, Item i, int prio) {
	struct q_element* ele;
	if ((ele = malloc(sizeof(struct q_element)))) {
		ele->data = i;
		ele->next = q->head;
		q->head = ele;
		return 1;
	}
	return 0;
}

Item q_remove(Queue* q) {
	struct q_element* ele;
	if ((ele = q->head)) {
		Item item = ele->data;
		q->head = ele->next;
		free(ele);
		return item;
	}
	return NULL;
}
