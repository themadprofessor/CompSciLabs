from collections import defaultdict
import re

def word_freq(data):
    alpha_regex = re.compile('[^a-z\s]+')
    
    freqs = defaultdict(int)
    for word in re.sub(alpha_regex, '', data.lower()).split():
        freqs[word] = freqs[word] + 1
    
    return freqs

assert({"the":2, "first":1, "test":1, "of":1, "function":1} == word_freq("The first test of the function."))
assert({} == word_freq(""))
assert({"doot":3} == word_freq("Doot doot DoOt"))
