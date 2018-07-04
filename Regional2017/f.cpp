#include <iostream>
#include <vector>
using namespace std;

const int MAX = 10000;

struct node{
    vector<int> children;
    int size;
    long npairs;
    long npairs2;
};

node nodes[MAX+1];
int maxIndex;
int count;

void dfs(int index, int prev, int n) // n = number of vertices-1
{
    node *nod = nodes+index;
    long np = 0;
    long sum = n;
    nod->size = 1;
    long large1=-1, large2=-1;
    for(vector<int>::iterator it = nod->children.begin(); it != nod->children.end(); it++) {
        if (*it == prev)
            continue;
        dfs(*it, index, n);
        long val = nodes[*it].size;
        sum -= val;
        nod->size += val;
        np += val*(n-val);
        if (val > large1) {
            large2 = large1;
            large1 = val;
        }
        else if (val > large2)
            large2 = val;
    }
    np += sum*(n-sum);
    if (sum > large1) {
        large2 = large1;
        large1 = sum;
    }
    else if (sum > large2)
        large2 = sum;
    nod->npairs = np/2;
    nod->npairs2 = np/2-large1*large2;
    if (maxIndex == -1) {
        maxIndex = index;
        count = 1;
    }
    else if (nod->npairs > nodes[maxIndex].npairs) {
        maxIndex = index;
        count = 1;
    }
    else if (nod->npairs == nodes[maxIndex].npairs) {
        count++;
    }
}

int main()
{
    int n;

    cin >> n;
    int n1, n2;
    for(int i=0; i<n; i++) {
        cin >> n1 >> n2;
        nodes[n1].children.push_back(n2);
        nodes[n2].children.push_back(n1);
    }
    maxIndex = -1;
    count = 0;
    dfs(0, -1, n);
    if (count > 1) {
        cout << "ERROR: duplicate answers" << endl;
    }
    else {
        cout << nodes[maxIndex].npairs << ' ' << nodes[maxIndex].npairs2 << endl;
    }
}
