import unittest
from src.module1 import MathOperations 

class TestMathOperations(unittest.TestCase):
    
    def test_func_add(self):
        self.assertEqual(MathOperations.func_add(5, 3), 8)
        self.assertEqual(MathOperations.func_add(-1, 1), 0)
        self.assertEqual(MathOperations.func_add(-1, -1), -2)

    def test_func_subtract(self):
        self.assertEqual(MathOperations.func_subtract(10, 4), 6)
        self.assertEqual(MathOperations.func_subtract(-1, -1), 0)
        self.assertEqual(MathOperations.func_subtract(-1, 1), -2)

    def test_func_multiply(self):
        self.assertEqual(MathOperations.func_multiply(7, 8), 56)
        self.assertEqual(MathOperations.func_multiply(-1, 1), -1)
        self.assertEqual(MathOperations.func_multiply(-1, -1), 1)
        self.assertEqual(MathOperations.func_multiply(0, 10), 0)