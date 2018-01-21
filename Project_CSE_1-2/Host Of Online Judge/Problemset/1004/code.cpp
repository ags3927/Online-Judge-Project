#include<bits/stdc++.h>
using namespace std;

int main()
{
    int T;
    cin>>T;
    while(T--)
    {
        vector<int>p;
        int n, i, j, k, x, ans=100000, a=0, b=0, ac=0, bc=0;
        cin>>n;
        for(i=0; i<n; i++){cin>>x; p.push_back(x);}

        for(i=0; i<n; i++)
        {
            j=i;
            while(a<180&&ac<(n-1)){a+=p[j]; j++; if(j==n){j=0;} }
            b=360-a;
            x=abs(a-b);
            ans=min(ans, x);
            a=0;
            b=0;
        }
        cout<<ans;
    }
    return 0;

}
