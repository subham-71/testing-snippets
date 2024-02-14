import unittest
from src.module1 import MathOperations  # Replace 'your_module' with the name of the Python file containing the MathOperations class

class TestMathOperationsIntegration(unittest.TestCase):
    
    def test_operations_sequence(self):
        # Step 1: Add two numbers
        result_add = MathOperations.func_add(10, 5)  # Expected result: 15
        
        # Step 2: Subtract a third number from the result of step 1
        result_subtract = MathOperations.func_subtract(result_add, 3)  # Expected result: 12
        
        # Step 3: Multiply the result of step 2 by a fourth number
        final_result = MathOperations.func_multiply(result_subtract, 2)  # Expected result: 24
        
        # Assert final result
        self.assertEqual(final_result, 24)