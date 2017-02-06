# Monte-Carlo
MonteCarlo Simulation

Problem statement:

Modern Portfolio Theory says that it is not enough to look at the expected risk and return of one particular stock. By investing in more than one stock, an investor can reap the benefits of diversification- chief among them, a reduction in the riskiness of the portfolio.

A Real Time implementation of Modern Portfolio Theory is to build an optimal asset allocation of domestic stocks & bonds, international stock and bonds, alternatives and cash. Combining the risk & return of each asset class and correlations across them – we come up with the risk and return of the overall portfolio. Two portfolios were created, one being extremely Conservative and one being Aggressive.

Examples

Portfolio Type - Return(Mean), Risk(Standard Deviation) 1. Aggressive - %9.4324, 15.675 2. Very Conservative - %6.189, 6.3438

We would now like to compare their performance against each other. We would like to know that if a user with $100,000 invested their money in either of them, how would the returns compare over the next 20 years. We would like to test their performance by using forward-looking Monte Carlo Simulations.

Monte Carlo Simulation: This is a statistical technique that uses pseudo-random uniform variables for a given statistical distribution based on past risk (SD) and return (mean) to predict outcomes over future time periods. Based on iterative evaluation of each random future value, we project the portfolio future value over 20 years. We would like to run 10,000 simulations of projecting 20 year value and come up with the following:

Assumptions

We would like to use a random number generator to ensure Gaussian distribution of random numbers that are generated.
20th Year future value should be inflation adjusted at the rate of 3.5% each year. Ie. Year 1 value of 103.5 is equivalent to 100 at Year 0.
Show the Portfolio Inflation Adjusted Values from Simulation - Median, 10% Best Case, 10% Worst Case.

10% Best Case: 90th Percentile value among the 10,000 simulations
10% Worst Case: 10th Percentile value among the 10,000 simulations.

Important calculations in solution

If a portfolio has historical mean returns of R with standard deviation of S, then a randomly generated nominal return using a Gaussian distribution is given by R + S * random.nextGaussian()

If nominal rate of return is N and rate of inflation is I, then real rate of return (or inflation adjusted rate of return) is given by ((1+N)/(1+I) - 1)

For N iterations run, Median, 90th Percentile and 10th Percentile are calculated with help of google guava plugin.

Explanation of Simulation is found in https://youtu.be/wKdmEXCvo9s and  https://www.youtube.com/watch?v=Q5Fw2IRMjPQ 

