//@author Gary
#include<iostream>
using namespace std;

int maxsize = 102;

char graph[102][102];

int offsetsX[8] = {-1,0,1,-1,1,-1,0,1};
int offsetsY[8] = {-1,-1,-1,0,0,1,1,1};

int output = 0;
int h,w;
void cancel(int i, int j){
    graph[i][j] = '.';
    for(int k = 0; k < 8;k++){
        if(graph[i+offsetsX[k]][j+offsetsY[k]]=='#'){
            cancel(i+offsetsX[k],j+offsetsY[k]);
        }
    }
}
void solve(int h, int w){
    for(int i = 1;i <= h ;i++){
            string line = "";
            cin>>line;
        for(int j = 1; j <= w; j++){
            graph[i][j]=line.at(j-1);
        }
    }
    for(int i = 1; i<=h;i++){
        for(int j = 1; j<=w; j++){
            if(graph[i][j]=='#'){
                cancel(i,j);
                output++;
            }
        }
    }
}
int main(){
    while(cin>>h>>w){
        solve(h,w);
        cout<<output<<endl;
        output = 0;
        for(int i = 0; i < 102; i++){
            for(int j = 0; j < 102; j++){
                graph[i][j] = '.';
            }
        }
    }
}
