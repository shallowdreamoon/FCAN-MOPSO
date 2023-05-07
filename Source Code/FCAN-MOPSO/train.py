from psoiternew import mainSingal
import time
import numpy as np
import os
import configparser

def readSettings(filename):
    infile=configparser.ConfigParser()
    infile.read(filename,"UTF-8")
    for option in infile.options('para'):
        if option == 'nv':
            nv = int(infile.get('para', option))
        elif option == 'k':
            k = int(infile.get('para', option))
        elif option == 'n':
            n = int(infile.get('para', option))
        elif option == 'w':
            w = float(infile.get('para', option))
        elif option == 'c1':
            c1 = float(infile.get('para', option))
        elif option == 'c2':
            c2 = float(infile.get('para', option))
        elif option == 'vmax':
            vmax = int(infile.get('para', option))
        elif option == 'vmin':
            vmin = int(infile.get('para', option))
        elif option == 'i':
            I = int(infile.get('para', option))
        elif option == 'iter_number':
            iter_number = int(infile.get('para', option))
        elif option == 'arfa':
            arfa = float(infile.get('para', option))
        elif option == 'beta':
            beta = float(infile.get('para', option))
        elif option == 'seta':
            seta = float(infile.get('para', option))
        elif option == 'fair':
            fair = float(infile.get('para', option))
        elif option=="d_filename":
            D_filename=infile.get('para',option)
        elif option=="a_filename":
            A_filename=infile.get('para',option)
        elif option=="result_savepath":
            result_savepath=infile.get('para',option)
    D=np.loadtxt(D_filename)
    A=np.loadtxt(A_filename)
    return nv,k,n,w,c1,c2,vmax,vmin,I,iter_number,arfa,beta,seta,fair,D,A,result_savepath

if __name__ == '__main__':
    
    start=time.time()
    filename = "dataset/cora/config.ini"
    nv, k, n, w, c1, c2, vmax, vmin, I, iter_number, arfa, beta, seta, fair, D, A, result_savepath = readSettings(
        filename)
    fitnesslist = mainSingal(iter_number, result_savepath,nv, k, n, w, c1, c2, vmax, vmin, I, arfa, beta, seta, fair, D, A)
    np.savetxt(result_savepath + "/fitness", fitnesslist, fmt="%.04f")
    end=time.time()
    print("Time cost：",end-start)






