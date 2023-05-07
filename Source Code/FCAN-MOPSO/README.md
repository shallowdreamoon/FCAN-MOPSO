# FCAN-MOPSO: An Improved Fuzzy-based Graph Clustering Algorithm for Complex Networks with Multi-objective Particle Swarm Optimization

This is a python implementation of the FCAN-MOPSO.

### Brief Description

Our experiments are mainly conducted on five datasets (Cora, Citeseer, Facebook, Twitter and Gplus).

For these datasets, Cora dataset is taken as an example to demonstrate in the source code. 

### Requirements

* python 3.8
* numpy

### Explanation

##### datasets: 
Since the inputs to FCAN-MOPSO are mainly the adjacency matrix D, the content relevance matrix A, and the coefficients associated with the algorithm, the data in the dataset directory correspond to each of these parts.

##### result:
"gbest" holds the optimal membership matrix U obtained by FCAN-MOPSO, and "fitness" holds the fitness values obtained by each iteration of FCAN-MOPSO.

### Running

```
python train.py
```


### Citation

If you use code or datasets in this repository for your research, please cite our paper.

@ARTICLE{10077417,
  author={Hu, Lun and Yang, Yue and Tang, Zehai and He, Yizhou and Luo, Xin},
  journal={IEEE Transactions on Fuzzy Systems}, 
  title={FCAN-MOPSO: An Improved Fuzzy-based Graph Clustering Algorithm for Complex Networks with Multi-objective Particle Swarm Optimization}, 
  year={2023},
  volume={},
  number={},
  pages={1-16},
  doi={10.1109/TFUZZ.2023.3259726}}



```python

```
