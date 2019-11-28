# Author: Jin Zhe

def input_val(message):
  return float(input(message))

def middle(n1, n2):
  return (n1 + n2)/2

def sqrt(n, error=1e-5):
  if n < 0:
    negError = ValueError('Input should be positive')
    raise myError

  if n < 1: # if n is smaller than 1, range to search is within [n, 1]
    lo = n
    hi = 1
  else:     # if n is greater than 1, range to search is within [0, n] 
    lo = 0
    hi = n
  
  mid = middle(lo, hi)
  while(abs(mid**2 - n) > error):
    if (mid**2 > n):
      hi = mid
    else:
      lo = mid
    mid = middle(lo, hi) # Update new mid
  return mid

def main():
  message = "Input float to carry out square root. Use a negative number to terminate."
  val = input_val(message)
  while val > 0:
    print("Float of {} is {}".format(val, sqrt(val)))
    val = input_val(message)

if __name__ == '__main__': main()