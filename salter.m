r = 1;

ys = ys + (rand(1,n)-0.5)*2*r;

scatter(xs, ys);
title("f(x) after salting, n=" + n + ", r=" + r);
xlabel("x");
ylabel("f(x)");