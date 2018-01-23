#include<bits/stdc++.h>
using namespace std;

int main()
{
    int T;
    cin>>T;
    while(T--)
    {
        int i, j, k, x, y, z, n;
        cin>>n;
        for(i=0; i<n; i++)
        {
            cin>>y;
            if(y>11){ cout<<"YES"<<endl; }
            else
            {
                if(y==3||y==6||y==7||y==9||y==10){ cout<<"YES"<<endl; }
                else{ cout<<"NO"<<endl; }
            }
        }
    }
    return 0;
}
