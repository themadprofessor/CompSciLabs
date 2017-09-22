def join(left, right):
    tmp = []
    for i in range(len(left)):
        tmp.append([left[i], right[i]])
    return tmp

def split(list):
    left = []
    right = []
    for pair in list:
        left.append(pair[0])
        right.append(pair[1])
    return left, right

left = [1,2,3]
right = [4,5,6]

assert join(left,right) == [[1,4],[2,5],[3,6]]

assert split(join(left,right)) == (left,right)

left = []
right = []
assert join(left,right) == []
