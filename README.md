# java-rmi-simulation
Java RMI simulation multiple server, SOEN 423, A1 Concordia university
This simulation is A1 of SOEN 423

## Author: Charles-Antoine Hardy , ID: 27417888
## Date: Fall 2018
## Course: SOEN 423
## Email: soen.hardy@gmail.com


### How to make it work ?
- Download and Extract the `.zip` folder
- You need to indicate some absolute path in the file `ServerConfigurator.java`
you need to replace `MAIN_TREE_FOLDER` and `CENTRAL_REPO_LOCATION` by
the location of the `storage folder` and the `CENTRAL` folder.


## Start CORBA

- You need eclipse
- You need a copy of this repo.
- You need Java from Oracle Java 8 should be fine

#### Step 1: Start the ORB
- Compile the `.idl` interface with: `idlj -fall yourname.idl`
- Compile the `.java` files with: `javac *.java yournameApp/*.java`
- Start (might want to kill process before) ORB with: `orbd -ORBInitialPort 1050&`

#### Step 2: Start the Server
- You should start your server with: `java youServerName.java -ORBInitialPort 1050 &`
- You could also use Eclipe and add as argument of the server `-ORBInitialPort 1050 &`

#### Step 3: Start the Client
- You should start the client with: `java youtClientName.java   -ORBInitialPort 1050 &`
- You could also use Eclipe and add as argument of the client `-ORBInitialPort 1050 &`

