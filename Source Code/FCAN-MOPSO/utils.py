"""
Mutation operation on matrix U;
Here are some functions related to mutation operations.
"""
import numpy as np
from classfitness import Fitness
import time
nv=2708
k=7

def mutation_coef(currentgen,totgen,mutrate=0.8):
    mutation=(1 - currentgen / totgen)**(5 / mutrate)
    return mutation

def ub_lb(a,b,mutrange,nv):
    ub=0
    lb=0
    for i in range(nv):
        ub=ub+a[i][b[i]]+mutrange
        lb=lb+a[i][b[i]]-mutrange
    return ub/nv,lb/nv

def ub_lb_constraint(ub,lb,min,max):
    if ub>max:
        ub=max
    if lb<min:
        lb=min
    return ub,lb

def update_matrix(a,b,lb,ub,nv):
    for i in range(nv):
        a[i][b[i]]=np.random.uniform(lb,ub)
    return a

def mutation_operator(a,k,nv,currentgen=10,totgen=10,min=0,max=1):
    # 0.Defining the coefficient of mutation
    mutation = mutation_coef(currentgen,totgen)

    # 3.Generate a random number of n
    b = np.random.randint(0, k, nv)

    # 4.Calculate the mutation range mutrange based on min,max and mutation
    mutrange = (max - min) * mutation

    # 5.Calculate ub and lb (summed average) based on mutrange and the values of n positions in the matrix
    ub, lb = ub_lb(a,b,mutrange,nv)

    # 6.Constraint processing, comparing the ub and lb values, with min and max, and determining whether to change them
    ub, lb = ub_lb_constraint(ub, lb, min, max)

    # 7.Update the values of n positions in the matrix by random generation, [lb,ub]
    a = update_matrix(a,b,lb,ub,nv)

    #Normalize a
    a=U_normalization(a)
    return a

#Single thread approach to join the mutation operation
def mutation_optimal(Pareto_Set,current_iter,total_iter, arfa, beta, seta, fair, D, A):
    mutation_time=time.time()

    #Save the Pareto_Set before the mutation
    P_Init = Pareto_Set.copy()
    P_Init_list = []
    for i in range(len(P_Init)):
        P_Init_obj = Fitness(arfa, beta, seta, fair, P_Init[i], D, A)
        P_Init_list.append(P_Init_obj.R())

    #Mutation of Pareto_Set
    Pareto_Set_fitness = [0] * nv
    for i in range(len(Pareto_Set)):
        Pareto_Set[i] = mutation_operator(Pareto_Set[i], k, nv,currentgen=current_iter,totgen=total_iter)
        Pareto_obj_temp = Fitness(arfa, beta, seta, fair, Pareto_Set[i], D, A)
        # if P_Set[i] is np.nan:
        #     P_Set[i] = 0
        Pareto_Set_fitness[i] = Pareto_obj_temp.R()

    Pareto_Set.extend(P_Init)
    Pareto_Set_fitness.extend(P_Init_list)

    # Select the global optimum in the mutated P_Set
    print("===============", Pareto_Set_fitness)
    max_fitness_value = max(Pareto_Set_fitness)
    print(max_fitness_value)
    max_fitness_value_index = Pareto_Set_fitness.index(max_fitness_value)
    print(max_fitness_value_index)
    U = Pareto_Set[max_fitness_value_index]
    print("=============================")
    print("Mutation time：{}".format(time.time()-mutation_time))
    return U

def My_Mutation(U, Pm=0.1):
    #Generate the number of nv with probability P prob=[]
    prob = np.random.choice([1, 0], size=nv, p=[Pm, 1-Pm])

    #Randomly generate nv numbers in the range of 0-k to determine the position of the mutation
    randnum = np.random.randint(0, k, nv)

    #If the i-th number in prob is 1, then the k-th number in the corresponding i-th row in U is mutated (in a randomly generated way)
    for i in range(nv):
        if prob[i]==1:
            U[i][randnum[i]]=np.random.random()

    #Normalization of U
    U = U_normalization(U)
    return U


def U_normalization(U):
    U[U<0]=0
    row_sum=sum(U.T)
    for i in range(U.shape[0]):
        if row_sum[i]!=0:
            U[i]=U[i]/row_sum[i]
    return U