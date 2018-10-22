#include <stdlib.h>
#include <string.h>
#include "queue.h"

struct list {
	Item data;
	struct list* next;
};

struct queue {
	struct list** q;
};

Queue* q_create(void) {
	Queue* queue;
	struct list** l;

	if ((queue = malloc(sizeof(Queue))) && (l = malloc(sizeof(struct list*) * MIN_PRIO))) {
		memset(l, (int) NULL, sizeof(struct list*) * MIN_PRIO);
		queue->q = l;
		return queue;
	}
	return queue;
}

int q_add(Queue* q, Item i, int prio) {
	struct list* l = q->q[prio-1];

	if (l == NULL) {
		if ((l = malloc(sizeof(struct list)))) {
			l->data = i;
			l->next = NULL;
			q->q[prio-1] = l;
			return 1;
		}
		return 0;
	}

	while (l->next != NULL) {
		l = l->next;
	}
	
	struct list* next;
	if ((next = malloc(sizeof(struct list)))) {
		next->next = NULL;
		next->data = i;
		return 1;
	}
	return 0;
}

Item q_remove(Queue* q) {
	for (int i = MIN_PRIO; i > 0; i--) {
		struct list* l = q->q[i-1];
		if (l != NULL) {
			Item item = l->data;
			q->q[i] = l->next;
			free(l);
			return item;
		}
	}
	return NULL;
}
