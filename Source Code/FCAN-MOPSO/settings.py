import numpy as np
import time
import os

nv = 2708  # 节点数
k = 7 # 聚类数
n = 10  # 初始化的粒子群数
w = 0.6  # 惯性权重
c1 = 1.7   #个体学习因子
c2 = 1.7  # c2为社会学习因子 ， c1和c2为学习权重
vmax=2
vmin=-2
I=10
iter_number=10

arfa = 0.5
beta = 0.5
seta = 1
fair = 1


start5 = time.time()
D=np.loadtxt("coraadjnaga.txt")
A=np.loadtxt("coracontent.txt")
print('矩阵读取', time.time() - start5,os.getpid())