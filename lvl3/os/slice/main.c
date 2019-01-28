//

// Created by stuart on 28/01/19.

//

#include <stdio.h>

int task1_iter = 10;

int task2_iter = 10;

int task3_iter = 10;

    static     int i;
    static     int j;
    static     int k;
// Declarations of per-line functions for task1
#define NINSTRS_TASK1 4
void (*task1_instrs[NINSTRS_TASK1]) (void);
void task1_line0(void);
void task1_line1(void);
void task1_line2(void);
void task1_line3(void);

// Declarations of per-line functions for task2
#define NINSTRS_TASK2 4
void (*task2_instrs[NINSTRS_TASK2]) (void);
void task2_line0(void);
void task2_line1(void);
void task2_line2(void);
void task2_line3(void);

// Declarations of per-line functions for task3
#define NINSTRS_TASK3 4
void (*task3_instrs[NINSTRS_TASK3]) (void);
void task3_line0(void);
void task3_line1(void);
void task3_line2(void);
void task3_line3(void);

struct Task {
    int pc;
    int count;
    void (**instructions)(void);
};

struct Task task1;
struct Task task2;
struct Task task3;

#define TASK_COUNT 3
struct Task* taskQueue[TASK_COUNT];
int currTask = 0;
int remainingTasks = TASK_COUNT;

static int pc;
static int system_timer;
static int blocking_timer;

int main(){ //}
    system_timer=0;
    blocking_timer=0;

    task1_instrs[0]=task1_line0;
    task1_instrs[1]=task1_line1;
    task1_instrs[2]=task1_line2;
    task1_instrs[3]=task1_line3;
    task1.pc = 0;
    task1.count = NINSTRS_TASK1;
    task1.instructions = task1_instrs;

    task2_instrs[0]=task2_line0;
    task2_instrs[1]=task2_line1;
    task2_instrs[2]=task2_line2;
    task2_instrs[3]=task2_line3;
    task2.pc = 0;
    task2.count = NINSTRS_TASK2;
    task2.instructions = task2_instrs;

    task3_instrs[0]=task3_line0;
    task3_instrs[1]=task3_line1;
    task3_instrs[2]=task3_line2;
    task3_instrs[3]=task3_line3;
    task3.pc = 0;
    task3.count = NINSTRS_TASK3;
    task3.instructions = task3_instrs;

    taskQueue[0] = &task1;
    taskQueue[1] = &task2;
    taskQueue[2] = &task3;

    while (1) {
        ++system_timer;
        struct Task* task = taskQueue[currTask];
        pc = task->pc;
        task->instructions[pc]();
        task->pc = pc;
        for (int l = 0; l < remainingTasks-1; ++l) {
            taskQueue[l] = taskQueue[l+1];
        }
        taskQueue[remainingTasks-1] = task;
        if (pc >= task->count) {
            --remainingTasks;
        }
        if (remainingTasks == 0) {
            break;
        }
    }

    return 0;
}

// Generated per-line function code for task1
void task1_line0(void) {pc++;}
void task1_line1(void) {        printf("1: %d\n", i);pc++;}
void task1_line2(void) {        i++;pc++;}
void task1_line3(void) {    if (i < task1_iter) { pc =0; } else { pc++;}}
// Generated per-line function code for task2
void task2_line0(void) {pc++;}
void task2_line1(void) {        printf("2: %d\n", j);pc++;}
void task2_line2(void) {        j++;pc++;}
void task2_line3(void) {    if (j < task2_iter) { pc =0; } else { pc++;}}
// Generated per-line function code for task3
void task3_line0(void) {pc++;}
void task3_line1(void) {        printf("3: %d\n", k);pc++;}
void task3_line2(void) {        k++;pc++;}
void task3_line3(void) {    if (k < task3_iter) { pc =0; } else { pc++;}}
