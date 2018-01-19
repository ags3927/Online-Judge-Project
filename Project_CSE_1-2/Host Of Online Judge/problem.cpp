#include<bits/stdc++.h>
using namespace std;

int main()
{
    int n,a,b,i,x=1,y=1,t,j;

    cin>> n;
    int num[101];
    memset(num,0,sizeof num);
    t=n;

    while(n--){
        cin>> i;
        num[i]++;
    }

    j=0;
    for(i=1;i<=100;i++){
        if(num[i]){
            if(!j)
                x=i;
            else
                y=i;
            j++;
        }
    }

    if(num[x]==num[y] && j==2 && num[x]+num[y]==t)
        cout<< "YES" << endl << x << ' ' << y;
    else
        cout<< "NO";

    return 0;
}

