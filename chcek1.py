def solution(S):
    # write your code in Python 3.6
    if S[0:2] == S[-1:-3]:
        print(len(S)-2)
    else:
        arr = []
        for i in range(len(S)-1):
            arr.append([(i+1),S[i:i+2]])
        print(arr)
        rev_arr = []
        j = -1;
        for c in arr:
            rev_arr.append(arr[j])
            j = j - 1
        print(rev_arr)
        ans_arr = []
        for i in range(len(arr)):
            index_v = rev_arr.index(arr[i])
            print("---------")
            print(index_v)
            print("---------")
            ans = (rev_arr[index_v][0]) - (arr[i][0])
            ans_arr.append(ans)
        answer = max(ans_arr)
        print(answer)
        # print(ans_arr)
        if answer == 0:
            return -1
        else:
            return (answer)

print solution("aabcaabcabda")
# print solution("aaa")
# print solution("codility")
