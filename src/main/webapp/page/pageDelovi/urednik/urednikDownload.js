naucnaCentrala.controller('urednikDownload', [
		'$scope',
		'$state',
		'$http',
		'FileSaver', 'Blob',
		function($scope, $state, $http, FileSaver, Blob) {

			$scope.radovi = [];
			
			$scope.preuzmiRad = function(y) {
			
				$http.post("http://localhost:8080/rad/download",y, {responseType: 'arraybuffer'}).then(
						function(response) {
							var myData = new Blob([response.data], { type: 'application/pdf;charset=utf-8' });
			                FileSaver.saveAs(myData, 'nesto.pdf');
						}, function(response){
							
						});
				
			};
			
			$scope.prikaziRadove = function() {
				$http.get("http://localhost:8080/rad/sviRadovi").then(
						function(response) {
							$scope.radovi = response.data;
						}, function(response) {
						});
			};


			$scope.prikaziRadove();

		} ]);