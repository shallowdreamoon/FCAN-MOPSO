import numpy as np

class Fitness():
    def __init__(self,arfa,beta,seta,fair,U,D,A):
        self.arfa=arfa
        self.beta=beta
        self.seta=seta
        self.fair=fair
        self.U=U
        self.D=D
        self.A=A
        # self.la=la


    def J(self,rowindex):
        U = self.U
        D = self.D
        A = self.A
        arfa = self.arfa
        beta = self.beta
        seta = self.seta
        fair = self.fair
        s = self.S(rowindex)
        s1=s[np.newaxis,:]
        U_rowindex_T = U[rowindex][:, np.newaxis]
        Arowindex_mul_U = np.dot(A[rowindex], U)
        Arowindex_mul_U_T = Arowindex_mul_U[np.newaxis, :]
        J1= arfa *np.trace(np.dot(U_rowindex_T, Arowindex_mul_U_T))

        Drowindex_mul_U = np.dot(D[rowindex], U)
        Drowindex_mul_U_T = Drowindex_mul_U[np.newaxis, :]
        J2 = beta *np.trace(np.dot(U_rowindex_T, Drowindex_mul_U_T))
        
        J3 = -seta * np.trace(np.dot(U_rowindex_T, s1))

        U_rowindex = U[rowindex][np.newaxis, :]
        J4= (-fair / 2) *np.trace(np.dot(U_rowindex_T, U_rowindex))
        
        return J1+J2+J3+J4


    # Fitness function
    def R(self):
        U = self.U
        D=self.D
        A=self.A
        arfa=self.arfa
        beta=self.beta
        seta=self.seta
        fair=self.fair
        s=self.S1()
        J1=arfa*np.trace(np.dot(np.dot(U.T,A),U))
        J2=beta*np.trace(np.dot(np.dot(U.T,D),U))
        J3=-seta*np.trace(np.dot(U.T,s))
        J4=(-fair/2)*np.trace(np.dot(U.T,U))
        return J1+J2+J3+J4


    #A single vector s
    def S(self,row):
        U=self.U
        D=self.D

        # The sum of each row of U
        sumU = sum(U.T)
        # Store the sum of each element of each row of the matrix D and the product of the corresponding U rows and the length of the list is the number of vertices
        sumDU = np.dot(D[row], sumU)
        # The value of the matrix at g = f
        sumUDK = np.dot(D[row], U)
        S = -1 * (sumUDK - sumDU)
        return S

    #Find the entire S matrix
    def S1(self):
        U = self.U
        D = self.D
        x = D.shape[0]
        y = U.shape[1]
        # The sum of each row of U
        sumU = sum(U.T)
        # Store the sum of each element of each row of the matrix D and the product of the corresponding U rows and the length of the list is the number of vertices
        sumDU = np.dot(D, sumU)
        # The value of the matrix at g = f
        sumUDK = np.dot(D, U)
        S = np.zeros((x, y))
        for i in range(x):
            for f in range(y):
                S[i, f] = sumDU[i] - sumUDK[i, f]
        return S

    def norm(self):
        U = self.U
        sum = [0] * U.shape[0]
        for i in range(U.shape[0]):
            sum[i] = 0
            for f in range(U.shape[1]):
                if U[i, f] > 0:
                    sum[i] += U[i, f]
                else:
                    U[i, f] = 0

        for i in range(U.shape[0]):
            x = sum[i]
            for f in range(U.shape[1]):
                U[i, f] = U[i, f] / x
        return U

# Define the functions in the PSO iteration
# Calculate the first term of the speed equation
def vpso(w, v):
    v=w*v
    return v

