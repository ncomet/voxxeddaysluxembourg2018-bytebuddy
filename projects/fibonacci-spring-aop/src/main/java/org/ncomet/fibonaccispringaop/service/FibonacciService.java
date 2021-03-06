package org.ncomet.fibonaccispringaop.service;

import org.springframework.stereotype.Service;

@Service
public class FibonacciService {

    public long compute(int n) {
        return n <= 1 ? n
                : compute(n - 2) + compute(n - 1);
    }

}
