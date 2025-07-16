properties([
    githubProjectProperty(displayName: '', projectUrlStr: 'https://github.com/AenArMarie/BSPB_Tests/'),
    buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '5', numToKeepStr: '3')),
    pipelineTriggers([githubPush()])
])

pipeline {
    agent any

    stages {
        stage('Smoke test') {
            steps {
                bat 'gradlew.bat clean test -Dcucumber.filter.tags="@smoke"'
            }
        }
        stage('Regression test') {
            steps {
                bat 'gradlew.bat clean test -Dcucumber.filter.tags="regression"'
            }
        }
        stage('Allure Report') {
                    steps {
                        allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
                    }
                }
    }
}