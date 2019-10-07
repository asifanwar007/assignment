# you can write to stdout for debugging purposes, e.g.
# print("this is a debug message")



n = list(map(int, input().split()))


min_n = min(n)
max_n = max(n)

arr_range= []
for c in range(min_n, max_n + 1):
    arr_range.append(c)

positive_n = 0
for c in n:
    if c > 0:
        positive_n += 1
n.sort()

new_n = []
for i in range(len(n)-1):
    if n[i] != n[i+1]:
        new_n.append(n[i])
if n[len(n)-1] != n[len(n)-2]:
    new_n.append(n[len(n)-1])

if arr_range == n and positive_n > 0:
    print(max_n + 1)
elif positive_n == 0:
    print(1)
else:
    for i in range(len(arr_range)):
        if new_n[i] != arr_range[i] and i < len(new_n):
            print(arr_range[i])
            break
        elif i == len(new_n) -1:
            print(max_n + 1)
            
    
    
