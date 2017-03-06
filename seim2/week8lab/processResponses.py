# Read in and display voting responses

from GUI import *

# For Part 1, add code here to process the files and produce the
# textual output.

def get_or(l, default, index):
    try:
        return l[index]
    except IndexError:
        l.extend([default] * (index - len(l) + 1))
        return default

def load_responses(question_defs, path="Lab Files/Responses.txt"):
    responses = []

    with open(path) as f:
        for line in f:
            split = line.strip().split()
            try:
                question_no = int(split[0])
                if question_no < 0 or question_no > len(question_defs):
                    raise ValueError
            except ValueError:
                print "Invalid question number! {}".format(split[0])
	        continue
            
            try:
                handset_id = int(split[1])
                if handset_id < 1 or handset_id > 160:
                    raise ValueError
            except ValueError:
                print "Invalid handset number! {}".format(split[1])
                continue

            try:
                response = int(split[2])
                if response < 0 or response > question_defs[question_no]["count"]:
                    raise ValueError
            except ValueError:
                print "Invalid response! {}".format(response)
                continue
            
            get_or(responses, {}, question_no)[handset_id] = response
    return responses

def load_questions(path="Lab Files/QuestionInfo.txt"):
    questions = [None]

    with open(path) as f:
        for line in f:
            split = line.strip().split()

            try:
                answer_no = int(split[1])
                if answer_no < 1 or answer_no > 10:
                    raise ValueError
            except ValueError:
                print "Invalid answer count! {}".format(split[1])
                return

            try:
                correct = int(split[0])
                if correct == 0:
                    correct = 10
                if correct < 0 or correct > answer_no:
                    raise ValueError
            except ValueError:
                print "Invalid correct answer! {}".format(split[0])

            questions.append({"correct": correct , "count": answer_no})

    return questions

def print_data(questions, responses):
    for question in xrange(1, len(questions)):
        print "Question {}".format(question)
        response_print = []

        for answer in xrange(1, questions[question]["count"] + 1):
            correct_count = responses[question].values().count(answer)
            if answer == questions[question]["correct"]:
                response_print.append("**{}:{}**".format(answer, correct_count))
            else:
                response_print.append("{}:{}".format(answer, correct_count))
        print " ".join(response_print)

questions = load_questions()
responses = load_responses(questions)
print_data(questions, responses)

# For Part 2, uncomment the line below (with suitable modifications) to
# start the graphical user interface.

graphs = application(len(questions)-1)
for i in xrange(len(graphs)):
     labels = range(1, questions[i+1]["count"]+1)
     data = []
     for answer in xrange(1, questions[i+1]["count"] + 1):
         count = responses[i+1].values().count(answer)
	 data.append(count)
     chart(graphs[i], labels, data, questions[i+1]["correct"])

complete()
# application(...) # The "..." indicates that parameters will be needed
