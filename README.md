# Mininet in sdn-app-v2

Create a virtual network which contains gateway, DHCP relay server, DHCP server and multiple end-hosts. It is used to simulate a multi-vlan network for enterprise network.

  - Gateway  -  Response for delivering packets from one subnet to another subnet (inter-vlan routing)
  - DHCP relay server  - Aside from gateway, use to pass and transform DHCP packet.
  - DHCP server  -  Assign IP address to multiple end-hosts

[Mininet] is a opensource software. It can creates a realistic virtual network, running real kernel, switch and application code, on a single machine (VM, cloud or native), in seconds, with a single command:


### Prerequisite
Ubuntu 14.04 or  12.04 supporting 802.1q module

Mininet 2.1.0



### Installation

Need DHCP relay server installed :

```sh
$ sudo apt-get install isc-dhcp-relay
```
 DHCP server:
```sh
$ sudo apt-get install isc-dhcp-server

```

### Experiment

Can either run Mininet and ODL in same machine or in different. 

Firstable open ODL with Sdn-app-v2, then change directory to estinet-karaf in sdn-app-v2-distribution

```sh
$ sudo ./target/assemble/bin/karaf clean
```
In Mininet folder there is a custom topology provided, it creates tree topology given level 2. You can type ...
```sh
$ sudo mn --custom ./tree4.py --topo mytopo --controller=remote,ip=[controller-ip]  --switch ovsk,protocols=OpenFlow13 --mac
```
Then Clean all the ip address of hosts
```sh
mininet> py execfile('cleanip.py')
```
Write white-list to datas-store according to mac address of each host in Mininet. You can either use restconf or IT to access data-store in ODL. For example in Karaf CLI:
```sh
root-opendaylight> it method data-store -f white-list
```
whitelist.txt file is located in sdn-app-v2-distribution/estinet-karaf/target/assemble/configuration/estinet/


Initialize Mininet (create gateway, dhcp server, etc.)
```sh
mininet> py execfile('Init.py')
```
Get IP addess from DHCP server and test connectivity
```sh
mininet> h2 dhclient h2-eth0
mininet> h5 dhclient h5-eth0
mininet> h2 ping h5
```

Cleaning up some process after stop Mininet
```sh
$ ./mnclean
```

Set number of host and DHCP server before experiment:
```sh
$ vim config.json
```




### Todo's


[Mininet]:http://mininet.org/
[john gruber]:http://daringfireball.net/
[@thomasfuchs]:http://twitter.com/thomasfuchs
[1]:http://daringfireball.net/projects/markdown/
[marked]:https://github.com/chjj/marked
[Ace Editor]:http://ace.ajax.org
[node.js]:http://nodejs.org
[Twitter Bootstrap]:http://twitter.github.com/bootstrap/
[keymaster.js]:https://github.com/madrobby/keymaster
[jQuery]:http://jquery.com
[@tjholowaychuk]:http://twitter.com/tjholowaychuk
[express]:http://expressjs.com
[AngularJS]:http://angularjs.org
[Gulp]:http://gulpjs.com
