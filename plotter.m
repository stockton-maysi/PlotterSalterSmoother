clear all;

a = -5;
b = 5;
n = 1000;

xs = zeros(1,n);
ys = zeros(1,n);

for i = 1:n
    x = a + (b-a)*i/n;
    xs(i) = x;
    ys(i) = f(x);
end

plot(xs, ys);
title("f(x), n=" + n);
xlabel("x");
ylabel("f(x)");

function y = f(x)
    y = sin(x^2);
end