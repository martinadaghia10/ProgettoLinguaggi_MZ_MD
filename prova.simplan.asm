//start MultiDecNode
move SP FP  
pushr FP 
move SP AL 
pushr AL 
//start DecNode
push 0 
//and DecNode
//start DecNode
push 0 
//and DecNode
//start FunNode
push function0
//end FunNode
//start AssNode
//start IntNode
storei A0 0
//end IntNode
move AL T1 
subi T1 1
load A0 0(T1) 
//and AssNode
//start CallFunNode
pushr FP 
move SP FP 
addi FP 1 
move AL T1
pushr T1 
//start IntNode
storei A0 10
//end IntNode
pushr A0
//start IntNode
storei A0 0
//end IntNode
pushr A0
move FP AL 
subi AL 1 
jsub function0
//end CallFunNode
//start IdNode
move AL T1 
subi T1 1
store A0 0(T1) 
//end IdNode
halt

function0:
pushr RA 
//start BodyNode
//start IfStmNode
//start LessEqualNode
//start IdNode
move AL T1 
subi T1 1
store A0 0(T1) 
//end IdNode
pushr A0 
//start IntNode
storei A0 0
//end IntNode
popr T1 
bleq T1 A0 label2 
storei A0 0
b label3 
label2: 
storei A0 1 
label3: 
//end LessEqualNode
storei T1 1 
beq A0 T1 label0
b label1
label0:
//start AssNode
//start IdNode
move AL T1 
subi T1 2
store A0 0(T1) 
//end IdNode
move AL T1 
store T1 0(T1) 
subi T1 1
load A0 0(T1) 
//and AssNode
label1:
//end IfStmNode
//start CallFunNode
pushr FP 
move SP FP 
addi FP 1 
move AL T1
store T1 0(T1) 
pushr T1 
//start SubNode
//start IdNode
move AL T1 
subi T1 1
store A0 0(T1) 
//end IdNode
pushr A0 
//start IntNode
storei A0 1
//end IntNode
popr T1 
sub T1 A0
popr A0 
//end SubNode
pushr A0
//start PlusNode
//start IdNode
move AL T1 
subi T1 2
store A0 0(T1) 
//end IdNode
pushr A0 
//start IdNode
move AL T1 
subi T1 1
store A0 0(T1) 
//end IdNode
popr T1 
add A0 T1 
popr A0 
//end PlusNode
pushr A0
move FP AL 
subi AL 1 
jsub function0
//end CallFunNode
addi SP 0
//and BodyNode
popr RA 
addi SP 2
pop 
store FP 0(FP) 
move FP AL 
subi AL 1 
pop 
rsub RA 
//end MultiDecNode
