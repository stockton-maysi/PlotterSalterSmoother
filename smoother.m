m = 10;

ysSmoothed = zeros(1,n);

for i=1:n
    if i <= m
        leftBound = 1;
        rightBound = i+m;
    elseif i+m > n
        leftBound = i-m;
        rightBound = n;
    else
        leftBound = i-m;
        rightBound = i+m;
    end

    ysSmoothed(i) = mean(ys(leftBound:rightBound));
end

scatter(xs, ysSmoothed);
title("f(x) after smoothing, n=" + n + ", r=" + r + ", m=" + m);
xlabel("x");
ylabel("f(x)");