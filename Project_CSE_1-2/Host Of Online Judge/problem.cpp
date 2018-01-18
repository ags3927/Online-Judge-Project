#include <cstdio>
#include <cstring>
#include <cstdlib>
#include <algorithm>
#include <complex>
using namespace std;

#define FR(i, a, b) for(int i = (a); i < (b); ++i)
#define FOR(i, n) FR(i, 0, n)
#define MP make_pair
#define A first
#define B second

typedef long long ll;
typedef complex<ll> pnt;

const int MAXN = 400;

#define X real
#define Y imag

pnt lis[MAXN];
int n;
int num[MAXN][MAXN];
int ans[MAXN];

ll cross(pnt a, pnt b) {
  return imag(conj(a) * b);
}

pnt getPoint() {
  int x, y;
  scanf("%d%d", &x, &y);
  return pnt(x, y);
}

int below(int i, int j) {
  return (X(lis[i]) == X(lis[j])) && (Y(lis[i]) < Y(lis[j]));
}

int betweenBelow(int i, int j, int x) {
  if (X(lis[i]) < X(lis[j])) {
    return X(lis[i]) < X(lis[x])  && X(lis[x]) < X(lis[j]) &&
      cross(lis[j] - lis[i], lis[x] - lis[i]) < 0;
  } else {
    return X(lis[j]) < X(lis[x])  && X(lis[x]) < X(lis[i]) &&
      cross(lis[i] - lis[j], lis[x] - lis[j]) < 0;
  }
}

int main() {
    scanf("%d", &n);
    FOR(i, n) {
        lis[i] = getPoint();
    }

      memset(num, 0, sizeof(num));
      FOR(i, n) {
        FOR(j, n) if(X(lis[i]) < X(lis[j])){
          FOR(k, n) if(k != i && k != j) {
            if(below(k, i)) num[i][j]++;
            if(below(k, j)) num[i][j]++;
            if(betweenBelow(i, j, k)) {
              num[i][j] += 2;
            }
          }
          num[j][i] = -num[i][j];
        }
      }

      memset(ans, 0, sizeof(ans));
      FOR(i, n) FOR(j, i) FOR(k, j) {
        int temp = abs(num[i][j] + num[j][k] + num[k][i]) / 2;
        temp -= betweenBelow(i, j, k);
        temp -= betweenBelow(j, k, i);
        temp -= betweenBelow(k, i, j);
        ans[temp]++;
      }
      FOR(i, n - 2) {
        printf("%d\n", ans[i]);
      }
      return 0;
}

