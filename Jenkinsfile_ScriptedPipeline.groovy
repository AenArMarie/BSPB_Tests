properties([
    githubProjectProperty(displayName: '', projectUrlStr: 'https://github.com/AenArMarie/BSPB_Tests/'),
    buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '5', numToKeepStr: '3')),
    parameters([booleanParam(defaultValue: false, name: 'remoteDriver')]),
    pipelineTriggers([githubPush()])
])

node {
    stage('Checkout') {
            git url: 'https://github.com/AenArMarie/BSPB_Tests.git', branch: 'master'
        }

    stage('Cleaning') {
        bat 'gradlew.bat clean'
    }

    stage('Smoke test') {
        catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
            bat 'gradlew.bat test -Dcucumber.filter.tags="@smoke"'
        }
    }

    stage('Regression test') {
        catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
            bat 'gradlew.bat test -Dcucumber.filter.tags="@regression"'
        }
    }

    stage('Allure Report') {
        allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']]
    }
}