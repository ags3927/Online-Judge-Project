#include<bits/stdc++.h>
using namespace std;

#define Max 100001
#define cnd1 ((a==3 && b==2) || (a==2 && b==1) || (a==1 && b==3))
#define cnd2 ((b==3 && a==2) || (b==2 && a==1) || (b==1 && a==3))
unsigned long long pnt[Max][2],al[4][4],bb[4][4],ta,tb;

int main()
{
    unsigned long long k,a,b,i,j,l,m,n,x,y;

    cin>> k >> a >> b;

    for(i=1;i<4;i++)
        for(j=1;j<4;j++)
            cin>> al[i][j];
    for(i=1;i<4;i++)
        for(j=1;j<4;j++)
            cin>> bb[i][j];

    i=1,l=a,m=b;
    if(a==b)
        pnt[i][0]=0,pnt[i][1]=0,i++;
    else if(cnd1)
        pnt[i][0]=1,pnt[i][1]=0,ta++,i++;
    else if(cnd2)
        pnt[i][0]=0,pnt[i][1]=1,tb++,i++;
    while(1){
        x=al[a][b],y=bb[a][b],a=x,b=y;

        if(a==b)
            pnt[i][0]=0,pnt[i][1]=0;
        else if(cnd1)
            pnt[i][0]=1,pnt[i][1]=0,ta++;
        else if(cnd2)
            pnt[i][0]=0,pnt[i][1]=1,tb++;


        if(a==l && b==m)
            break;

        i++;
    }



    unsigned long long cnt1,cnt2;
    cnt1=(k/i)*ta,cnt2=(k/i)*tb;

    if(i<=k)
        x=k%i;
    else
        x=k;

    for(i=1;i<=x;i++){
        cnt1+=pnt[i][0],cnt2+=pnt[i][1];
    }

    cout<< cnt1 << ' ' << cnt2 << endl;

    return 0;
}

