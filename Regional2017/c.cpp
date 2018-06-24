// @author Qiwen Luo
#include<iostream>
#include<string>
using namespace std;
class Drm{
public:
    string decode(string input){
        string output;
        int rotateValue1 = 0,rotateValue2 = 0,i,j;
        for(i = 0;i < input.size()/2;i++){
        rotateValue1 = (rotateValue1 + input.at(i)-'A') % 26;
    }

    for(;i < input.size();i++){
        rotateValue2 = (rotateValue2 + input.at(i)-'A') % 26;
    }

    for(j = 0;j < input.size()/2;j++){
        int value1 = input.at(j);
        int value2 = input.at(input.size()/2+j);
        int value = (value1-'A'+rotateValue1+value2-'A'+rotateValue2)%26 + 'A';
        output.push_back((char)value);
    }
    return output;
    }
};
int main(){
    string input;
    Drm drm;
    while(cin>>input){
        cout<<drm.decode(input)<<endl;
    }
}
