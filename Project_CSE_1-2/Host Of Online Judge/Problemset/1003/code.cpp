#include<bits/stdc++.h>
using namespace std;

long long int f(long long int n, long long int r)
{
     if(n==r) return 1LL;
     if(r==1) return n;
     return f(n-1,r) + f(n-1,r-1);
}

int main()
{
    int i, T, j, k;
    int len=0;
    char s[105];
    cin>>T;
    scanf("\n");
    while(T--)
    {

        long long int x=0, y=0, ans=0, z=0, a=0, b=0;
        gets(s);
        //puts(s);
        len=strlen(s);
        for(i=1; i<(len-1); i++)
        {

            if(s[i]=='A')
            {
                for(j=i-1; j>=0; j--)
                {
                    if(s[j]=='Q'){x++;}

                }

                for(j=i+1; j<len; j++)
                {
                    if(s[j]=='Q'){y++;}

                }
                z=x*y;
                ans+=z;
                z=0;
                x=0;
                y=0;
            }
        }
        cout<<ans<<endl;
    }
    return 0;
}
