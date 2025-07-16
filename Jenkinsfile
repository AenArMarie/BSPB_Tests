properties([
    githubProjectProperty(displayName: '', projectUrlStr: 'https://github.com/AenArMarie/BSPB_Tests/'),
    buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '5', numToKeepStr: '3')),
    pipelineTriggers([githubPush()])
])

pipeline {
    agent any

    stages {
        stage('Cleaning') {
                    steps {
                        bat 'gradlew.bat clean'
                    }
                }
        stage('Smoke test') {
            steps {
                bat 'gradlew.bat test -Dcucumber.filter.tags="@smoke"'
            }
        }
        stage('Regression test') {
            steps {
                bat 'gradlew.bat test -Dcucumber.filter.tags="@regression"'
            }
        }
    }

    post {
            always {
                allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']]
            }
        }
}