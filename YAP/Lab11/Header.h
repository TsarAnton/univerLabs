#ifndef Header_h
#define Header_h
#include "Stack.h"
#include <string>
using namespace std;

class Group {
private:
	string name;
	Stack<User> users;
	Stack<weak_ptr<Message>> messages;
public:
	Group(string name);
	~Group();
	//void add_user(User user, shared_ptr<Group> &group);
	friend void add_message(shared_ptr<Message>& message, shared_ptr<Group>& group);
};

#endif
