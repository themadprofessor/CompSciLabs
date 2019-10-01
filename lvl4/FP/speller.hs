is_for :: [Char] -> [Char]
is_for x = ((x!!0) : " is for ") ++ x

last_only :: (a->a) -> [a] -> [a]
last_only _ [] = []
last_only f [x] = [f x]
last_only f (x:xs) = x : (last_only f xs)

except_last :: (a->a) -> [a] -> [a]
except_last _ [] = []
except_last _ [x] = [x]
except_last f (x:xs) = (f x) : except_last f xs

build_list :: [[Char]] -> [[Char]]
build_list xs = except_last (\x->x++", ") (last_only (\x->"and " ++ x) (map is_for xs))

speller :: [[Char]] -> [Char]
speller xs = foldl (\acc x->acc ++ x) "" (build_list xs)
