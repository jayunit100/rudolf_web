Clojure coding standards
==========



Preamble and Philosophy
-----

These are not hard and fast rules, but rather suggestions.  The spirit should be similar to that of Python's Pep 0008:  follow these rules if you can, but feel free to break them if you need to.

The purpose is to help us stay out of trouble:  as we know, Clojure is a very difficult language.  If you get stuck, you can look here for suggestions.  

This will also help us to work together by making the code look more uniform.

We should value consistency, but not as a end unto itself -- only as a means to help us communicate and work with other Clojure programmers more effectively.



Whitespace
------

 - blank lines?  number of lines between functions?




Imports
-------

 - don't use `:use` in the ns macro; use `:require` instead.  Rationale:  it's extremely hard to figure out where symbols are coming from if there's multiple libraries being `:used`.  Example:

      (ns my.name.space
        (:require [clojure.something  :as sthng])
        ...)



Parentheses
------

 - closing ones shouldn't get their own line.  Example:

      (+ 3 (- 1 2)
         )

  is bad.  Better:

      (+ 3 (- 1 2))



Indentation
------

We don't know what the real clojure standards are, if any.

Nevertheless, some suggestions:

 - two spaces for "first" level, then one space thereafter

      (defn 
        [] ;; two spaces!!
        (+ 3 2))

 - align items in the same list horizontally (if they're on separate lines)

      (+
       (* 1 2)
       (* 3 4))



Comments
------

 - **don't put things in comments that belong in docstrings!!**

 - number of semicolons:  how many?  1?  2?

 - do they get their own line or not?



