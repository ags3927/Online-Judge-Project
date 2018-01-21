#include<bits/stdc++.h>
using namespace std;

int flag[6];

int main()
{
    int T;
    cin>>T;
    while(T--)
    {
        vector<int>v;
        int i, j, k, x, y, z, a[6], b, c, f1=0,sum=0;
        cin>>a[0]>>a[1]>>a[2]>>a[3]>>a[4]>>a[5];
        for(i=0; i<6; i++)
        {
            flag[i]=0;
        }
        for(i=0; i<6; i++)
        {
            for(j=0; j<6; j++)
            {
                if(j==i)
                {
                    sum=0;
                    continue;
                }
                for(k=0; k<6; k++)
                {
                    if(k==j||k==i)
                    {
                        sum=0;
                        continue;
                    }
                    for(x=0; x<6; x++)
                    {
                        if(x!=i&&x!=j&&x!=k){sum+=a[x];}
                    }
                    if(sum==(a[i]+a[j]+a[k])){cout<<"YES"<<endl; f1=1; break;}
                    sum=0;
                }
                if(f1==1){break;}
            }
            if(f1==1){break;}
        }
        if(f1!=1){
            cout<<"NO"<<endl;
        }
    }
    return 0;
}