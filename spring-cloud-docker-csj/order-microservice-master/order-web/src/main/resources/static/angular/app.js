var readyDataUrl = '@Url.Content("./list")';
var loadDataUrl = '@Url.Content("./list")';
var app = angular.module('app', ['ui.bootstrap']);
app.controller('ctrl', ['$log', '$http', '$scope', function ($log, $http, $scope) {
	$scope.reportData = [];
	$scope.maxSize = 7;
	$scope.currentPage = 0;
	$scope.totalItems = 0;
	$scope.pageChanged = function () {
		//showLoading("正在查询");
		$http.post(loadDataUrl, {
			pageIndex: $scope.currentPage,
			pageSize: 10,
			name: ""
		})
			.then(function (result) {
				$scope.reportData = result.content;
				$scope.totalItems = result.totalElements;
			}).catch(function (error) {
				$log.error('error:' + error);
			}).finally(function () {
				//closeLoading();
			});
	}
	$scope.Inital = function () {
		//showLoading("正在查询");

		$http.post(readyDataUrl, {
			pageIndex: $scope.currentPage,
			pageSize: 10,
			name: ""
		}).then(function (result) {
			$scope.reportData = result.content;
			$scope.totalItems = result.totalElements;
			//closeLoading();
		}).catch(function (error) {
			$log.error('error:' + error);
		}).finally(function () {

		});
	}
	$scope.Inital();
	$scope.search = function () {
		//showLoading("正在查询");
		$http.post(loadDataUrl, {})
			.then(function (result) {
				$scope.reportData = result.content;
				$scope.totalItems = result.totalElements;
			}).catch(function (error) {
				$log.error('error:' + error);
			}).finally(function () {
				//closeLoading();
			});
			}
		}]);