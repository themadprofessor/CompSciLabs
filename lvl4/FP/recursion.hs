mysum :: Int -> Int
mysum 0 = 0
mysum x = x + (mysum (x-1))

fib :: Int -> Int
fib 0 = 0
fib 1 = 1
fib x = fib (x-1) + fib (x-2)

myreplicate :: Int -> [Int]
myreplicate x = take x (repeat x)

sumacc :: Int -> Int -> Int
sumacc 0 acc = acc
sumacc x acc = sumacc (x-1) (acc+x)

fibacc :: Int -> Int -> Int
fibacc 0 acc = acc
fibacc 1 acc = acc + 1
fibacc x acc = fibacc (x-2) (acc + (x-1))

prodList :: [Int] -> Int
prodList [] = 0
prodList [x] = x
prodList (x:xs) = x * (prodList xs)

lengthOfList :: [Int] -> Int
lengthOfList [] = 0
lengthOfList (x:xs) = 1 + (lengthOfList xs)

reverseList :: [a] -> [a]
reverseList [] = []
reverseList [x] = [x]
reverseList (x:xs) = (reverseList xs) ++ [x]

maxInList :: [Int] -> Int
maxInList [] = 0
maxInList [x] = x
maxInList [x,y] = if x > y then x else y
maxInList (x:xs) = if x > y then x else y
  where y = maxInList xs
