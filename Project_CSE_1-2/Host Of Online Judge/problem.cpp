#include<bits/stdc++.h>
using namespace std;

int arr[105];

int main()
{
    int T;
    cin>>T;
    while(T--)
    {
        int a=1, x, b=1, c=0, f=0, i, j, k, n;
        cin>>n;
        for(i=0; i<n; i++)
        {
            cin>>arr[i];
        }
        for(i=0; i<n; i++)
        {
            if(arr[i]==1){a=1; swap(b, c);}
            else if(arr[i]==2){b=1; swap(a, c);}
            else if(arr[i]==3){c=1; swap(a, b);}
            if(a==1&&b==1&&c==1){cout<<"NO"; f=1; break;}
        }
        if(f==0){cout<<"YES";}
    }
    return 0;

}

