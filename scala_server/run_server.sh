 #!/bin/bash
#########################################################
#  setup script for low level compressor project                                                      
#  author : Kira Chu 
#  usuage: ./run_server.sh -c [config.yml]             
#########################################################

function runServer {	
	PO=$(cat $1 | grep " port:" | sed s,port\\:,,g )
	ADMIN=$(cat $1 | grep " adminPort:" | sed s,adminPort\\:,,g ) 
	echo -e "Run Drop-Wizard Server on \n\t service port:${PO} \n\t admin port: ${ADMIN} "
	java -jar ./target/tutulu_server-1.0-SNAPSHOT.jar server $1  
}

function refactorSample {
	egrep -lRZ "scalaHello" .  | xargs -0 -l sed -i -e 's/scalaHello/$1/g' &
	egrep -lRZ " Hello" .  | xargs -0 -l sed -i -e 's/ Hello/ $2/g'
	egrep -lRZ "Hello" .  | xargs -0 -l sed -i -e 's/Hello/Tutulu/g'

}

function runDB {
	mongod & redis-server
}
######################################################
#                    main part                       #                    
######################################################

defaultc="./sample.yml"
config=$defaultc

opts=":r:c:phd"
while getopts "$opts" optchar; do
	case "${optchar}" in 	
		p)
 			echo "repackaging ..."
 			#re-package
		 	BUILD=$( mvn package | egrep -c "BUILD SUCCESS" ) 
		 	if (( !BUILD ));then 
		 		echo "BUILD FAIL:"
		 		exit 1
		 	fi
 			;;
		c)
			#run server on configuration file: config.yml

			if (( $( echo $OPTARG | egrep -c ".(yml|yaml)$") && $( find -wholename $OPTARG | wc -l  ) ));
				then
				config=$OPTARG
				
			else 
				echo  -e "bad yml config file or it does not exist...\n"
			fi
			;;
		h)
 			#help
 			echo -e "./run_server.sh  -c [config.yml]\n\t\t -p [repackage]  "
 			exit 0
 			;;
 		r)
 			refactorSample $OPTARG 
 			exit 0 
 			;;
 		d)
 			runDB 
 			exit 0
 			;;
		*)
			if [ "$OPTERR" != 1 ] || [ "${opts:0:1}" = ":" ]; then
                echo "Non-option argument: '-${OPTARG}'" >&2
            fi
               	echo "argument error please check: run_server -h"
            ;;
       	esac
done 

#check if the jar exist
if(( !$(find -wholename "./target/tutulu_server-1.0-SNAPSHOT.jar"| wc -l) ));then 
					mvn package
fi

runServer $config
