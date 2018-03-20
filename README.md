# intro-to-ml
This is playground project. Example problems and introducing concepts used in Machine Learning. 

# Setup
Install _jupyter notebook_ and _jupyter notebook scala kernel_. Continue in [Setup.ipynb](Setup.ipynb).


# How to tensorboard

* See `HelloScalaTensorflow1.scala` for see how to log events to tensorboard
* This is how you run tensor board:

```bash
tensorboard --logdir=<path>
``` 

# Example problems 

Goal: 
  Try to train fully connected network solving below problems.

-- easy problems

f(x) = if(x < a) 0 else 1
f(x0, x1) = if(x0 < x1) 0 else 1
f(x0, x1) = if(x0 < x1) x0*x1 else x0/x1


f(x0, x1) = x0 && x1 
f(x0, x1) = x0 || x1 
f(x0, x1) = x0 xor x1  


-- tricky problems

f(s: String_of_10_chars) = if(s.contains("A.BC") ) 1 else 0

f(x0, x1) = x0 % x1

f(x0, x1, x2) = (x0 to x1 by x2).avg


