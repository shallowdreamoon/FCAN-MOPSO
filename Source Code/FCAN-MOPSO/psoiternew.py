import numpy as np
import time
import classfitness
from classfitness import Fitness
from utils import My_Mutation
import matplotlib.pyplot as plt
import random
from multiprocessing import Pool,cpu_count
from multiprocessing import Process
import os
import configparser
import threading

# Store n particles in a list
def InitParticalSwarm(n,k):
    # Initialize n one-dimensional particles and normalize them
    row = [0] * n
    for i in range(n):
        row[i] = np.random.random((1, k))
        row[i] = normalization(row[i])
    return row

#Solve for the fitness value of each particle and initialize the local optimal solution of each particle
def InitSwarmPbest(u,row,rowindex,n,arfa, beta, seta, fair,D,A):
    # Initialize local optimal solution and local optimal value
    every_partical_pbestfit_list = [0]*n
    every_partical_pbest = [0] * n
    for i in range(n):
        u[rowindex] = row[i]
        every_partical_obj = Fitness(arfa, beta, seta, fair, u, D, A)
        every_partical_fit = every_partical_obj.J(rowindex)
        every_partical_pbestfit_list[i]=every_partical_fit
        every_partical_pbest[i] = row[i]
    return every_partical_pbest,every_partical_pbestfit_list

#Initialize global optimal solution and local optimal value
def InitSwarmGbest(every_partical_pbest,every_partical_pbestfit_list,n):
    every_partical_gbestfit = float('-inf')
    for i in range(n):
        if every_partical_pbestfit_list[i] > every_partical_gbestfit:
            every_partical_gbestfit = every_partical_pbestfit_list[i]
            every_partical_gbest = every_partical_pbest[i]
        else:
            pass
    return every_partical_gbest,every_partical_gbestfit


# Initialize the velocity of each particle in each dimension
def InitParticalV(n,k):
    # Initialize a velocity for each particle
    v = [0] * n
    for i in range(n):
        v[i] = np.random.uniform(-1, 1, (1, k))
    return v

#Normalization of row vectors
def normalization(row):
    row=row/sum(row.T)
    return row

#Multi-threaded update of particle swarm, if needed
def taskOne(i,w,c1,c2,row,v,every_partical_pbest,every_partical_gbest,vmax,vmin):
    r1, r2 = np.random.random(), np.random.random()
    v[i] = w * v[i][0] + c1 * r1 * (every_partical_pbest[i] - row[i]) + c2 * r2 * (every_partical_gbest - row[i])
    v[i][v[i] > vmax] = vmax
    v[i][v[i] < vmin] = vmin
    row[i] = row[i] + v[i]
    row[i][row[i] < 0] = 0
    row[i] = normalization(row[i])

# Solving the subproblem of FCAN-MOPSO: PSO update iterative search for optimal
def PsoIter( u,row,every_partical_pbest,every_partical_pbestfit_list,every_partical_gbest,every_partical_gbestfit,v,rowindex,
             n, w, c1, c2, vmax, vmin, I, arfa, beta, seta, fair, D, A):
    # pso update iterates all particles, i.e., update particle swarm
    for iter in range(I):
        #thread_oneList=[]
        #start_xxx=time.time()
        for i in range(n):
            #Non-multithreaded
            r1, r2 = np.random.random(), np.random.random()
            v[i] = w * v[i][0] + c1 * r1 * (every_partical_pbest[i] - row[i]) + c2 * r2 * (every_partical_gbest - row[i])
            v[i][v[i] > vmax] = vmax
            v[i][v[i] < vmin] = vmin
            row[i] = row[i] + v[i]
            row[i][row[i] < 0] = 0
            row[i] = normalization(row[i])
            #Multi-threaded
        #     thread_one=threading.Thread(target=taskOne,args=(i,w,c1,c2,row,v,every_partical_pbest,every_partical_gbest,vmax,vmin))
        #     thread_one.start()
        #     thread_oneList.append(thread_one)
        # for t in thread_oneList:
        #     t.join()
        #print(time.time()-start_xxx)

        # Update Local Optimal
        for i in range(n):
            u[rowindex] = row[i]
            every_partical_pbestobj = Fitness(arfa, beta, seta, fair, u, D, A)
            every_partical_fitpre = every_partical_pbestobj.J(rowindex)
            if every_partical_pbestfit_list[i] < every_partical_fitpre:
                every_partical_pbestfit_list[i] = every_partical_fitpre
                every_partical_pbest[i] = row[i]
        # Update Global Optimal
        for i in range(n):
            if every_partical_pbestfit_list[i] > every_partical_gbestfit:
                every_partical_gbestfit = every_partical_pbestfit_list[i]
                every_partical_gbest = every_partical_pbest[i]
        u[rowindex] = every_partical_gbest
    u[rowindex] = every_partical_gbest
    return every_partical_gbest, every_partical_gbestfit

# The entire process of FCAN-MOPSO
def TotalIter(iter_number,result_savepath,nv, k, n, w, c1, c2, vmax, vmin, I, arfa, beta, seta, fair, D, A, mutation=False):
    bestfitvalue = []
    all_bestfitlist=[]
    u_init = np.loadtxt(result_savepath + "/gbest")
    objinit = Fitness(arfa, beta, seta, fair, u_init, D, A)
    gbestfitpre = objinit.R()
    all_bestfitlist.append(gbestfitpre)
    #gbestfitpre=float("-inf")
    for i in range(iter_number):
        # u = np.loadtxt('gbest.txt')
        u = np.loadtxt(result_savepath+"/gbest")
        bestPre=u.copy()
        if k==1:
            u=u[:,np.newaxis]
        best_U = np.zeros((nv, k))

        #Pareto set
        Pareto_Set = []

        # solving each subproblem
        for rowindex in range(nv):
            #tempMat=u.copy()

            row = InitParticalSwarm(n,k)
            every_partical_pbest, every_partical_pbestfit_list = InitSwarmPbest(u, row,rowindex,n,arfa, beta, seta, fair,D,A)
            every_partical_gbest, every_partical_gbestfit = InitSwarmGbest(every_partical_pbest,
                                                                           every_partical_pbestfit_list, n)
            v = InitParticalV(n,k)
            every_partical_gbest, every_partical_gbestfit = PsoIter(u, row, every_partical_pbest,
                                                                    every_partical_pbestfit_list, every_partical_gbest,
                                                                    every_partical_gbestfit, v,rowindex,n, w, c1, c2, vmax, vmin, I, arfa, beta, seta, fair, D, A)
            best_U[rowindex]=every_partical_gbest
            bestfitvalue.append(every_partical_gbestfit)
            print('Iteration: ',i,' subproblem: ', rowindex, ' the optimal solution is：', every_partical_gbestfit)

            #Take the best_U obtained from each single target as nondominated_solution, and store it in Pareto_Set
            temp_U = best_U.copy()
            Pareto_Set.append(temp_U)

        if mutation:
            #Mutation and Optimization Operations
            #Mutation
            Pareto_Set_fitness = [0] * nv
            for num in range(len(Pareto_Set)):
                Pareto_Set[num] = My_Mutation(Pareto_Set[num])
                P_temp_obj = Fitness(arfa, beta, seta, fair, Pareto_Set[num], D, A)
                Pareto_Set_fitness[num] = P_temp_obj.R()
            #Optimization
            max_fitness_value = max(Pareto_Set_fitness)
            print(max_fitness_value)
            max_fitness_value_index = Pareto_Set_fitness.index(max_fitness_value)
            print(max_fitness_value_index)
            best_U = Pareto_Set[max_fitness_value_index]

        objout=Fitness(arfa, beta, seta, fair, best_U, D, A)
        all_bestfit=objout.R()
        all_bestfitlist.append(all_bestfit)
        if all_bestfit-gbestfitpre<=0.1:
            np.savetxt(result_savepath+"/gbest",bestPre,fmt="%.04f")
            iterAllNum=i
            break
        else:
            gbestfitpre=all_bestfit
            np.savetxt(result_savepath+"/gbest", best_U, fmt="%.04f")

        print("===== Iteration: {}: all_bestfit: {} =====".format(i, all_bestfit))
        
    return bestfitvalue,all_bestfitlist



def initial_rand_matrix(nv,k,arfa, beta, seta, fair, D, A,result_savepath):
    u = np.random.random((nv, k))
    # Normalization of U
    objinit = Fitness(arfa, beta, seta, fair, u, D, A)
    u = objinit.norm()
    np.savetxt(result_savepath+"/gbest", u, fmt='%.04f')

def mainSingal(iter_number, result_savepath,nv, k, n, w, c1, c2, vmax, vmin, I, arfa, beta, seta, fair, D, A):
    iterAllNum = iter_number
    initial_rand_matrix(nv, k, arfa, beta, seta, fair, D, A, result_savepath)
    bestfitvalue, all_bestfitlist = TotalIter(iter_number, result_savepath, nv, k, n, w, c1, c2, vmax, vmin, I, arfa,
                                              beta, seta, fair, D, A)
    return all_bestfitlist

