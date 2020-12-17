#!groovy
/*
======================================
Author:DevOps
Date: 17/12/2020
Purpose: To build frontend java project
=====================================
 */

pipeline {
    agent {
        label 'master'
    }

    tools {
        maven 'mymaven'
        jdk 'java11'
    }

    stages {
        stage ('Code Validate') {
            steps {
                scripts {
                    sh """

                mvn validate
                
                        """
                }
            }
        }

        stage ('Code Compile') {
            steps {
                scripts {
                    sh """

                mvn compile
                
                        """
                }
            }
        }

        stage ('Code Test') {
            steps {
                scripts {
                    sh """

                mvn test
                
                        """
                }
            }
        }

        stage ('Code Package') {
            steps {
                scripts {
                    sh """

                mvn package
                
                        """
                }
            }
        }

        stage ('Code Zip') {
            steps {
                scripts {
                    sh """
                cd "${env.WORKSPACE}/target/"
                zip FrontEnd.zip addressbook-2.0.war
              
                       """
                }
            }
        }

    }
}

/* Clean the work space */

post{

    always{

        dir("${env.WORKSPACE}@tmp"){
            deleteDir()
        }

    }
}


