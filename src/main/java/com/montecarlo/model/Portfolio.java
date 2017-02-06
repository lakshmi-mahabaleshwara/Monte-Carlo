package com.montecarlo.model;

import com.montecarlo.util.PortfolioType;

/**
 * This class holds all portfolio data
 * Created by lmahabaleshwara on 2/4/17.
 */
public class Portfolio {
    private final PortfolioType portfolioType;
    private final double risk;
    private final double returns;

    public Portfolio(PortfolioType portfolioType, double returns, double risk) {
        this.portfolioType = portfolioType;
        this.risk = risk;
        this.returns = returns;
    }

    public PortfolioType getPortfolioType() {
        return portfolioType;
    }

    public double getReturns() {
        return returns;
    }

    public double getRisk() {
        return risk;
    }
}
