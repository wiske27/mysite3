(function() {

    var db = {

        loadData: function(filter) {
            /*
        	return $.grep(this.clients, function(client) {
                return (!filter.Name || client.Name.indexOf(filter.Name) > -1)
                    && (filter.Age === undefined || client.Age === filter.Age)
                    && (!filter.Address || client.Address.indexOf(filter.Address) > -1)
                    && (!filter.Country || client.Country === filter.Country)
                    && (filter.Married === undefined || client.Married === filter.Married);
            });
            */
        	
        	
        	var d = $.Deferred();
        	
        	// 서버에서 AJAX로 데이터를 가져와서
        	// 배열 this.contents에 추가
        	$.ajax({
        		url: "/mysite3/grid/api/list",
        		type: "get",
        		datatype: "json",
        		success: function(response) {
        			$.each(response, function(index, vo){
        				db.clients.push( db.toGridData(vo) );
        			});
        			
        			d.resolve( db.clients);
        		}
        	});
        	
        	return d.promise();
        },

        insertItem: function(insertingClient) {
        	
        	var vo = db.toVoData(insertingClient);
        	console.log(vo);
        	$.ajax({
				url : "/mysite3/grid/api/insert",
				type: "post",
				data: JSON.stringify(vo),
				dataType: "JSON",
				contentType: "application/json",
				success: function(response) {
					console.log(response);
					db.clients.push(db.toVoData(response));
				}
			});

        },

        updateItem: function(updatingClient) { 
        	console.log(updatingClient);
        	
        },

        deleteItem: function(deletingClient) {
        	console.log(deletingClient);
        	
        	$.ajax({
        		url: "/mysite3/grid/api/delete?no=" + deletingClient["No"],
        		type: "get",
        		data: "",
        		dataType: "json",
        		success: function (response) {
        			console.log(response);
        			//var clientIndex = $.inArray(deletingClient, db.clients);
                    //this.clients.splice(clientIndex, 1);
        		}
        	});
            
        },
        
        toGridData: function(vo) {
        	var data = {};
        	
        	data["No"] = vo["no"];
        	data["Name"] = vo["name"];		//vo.name
        	data["Age"] = vo["age"];		
        	data["Country"] = vo["country"];		
        	data["Address"] = vo["address"];		
        	data["Married"] = ( vo["married"] == "0" ) ? false : true;		
        	
        	return data;
        },
        
        toVoData : function( data ) {
        	var vo  = {};
        	
        	//vo["no"] = data["No"];
        	vo["name"] = data["Name"];		//vo.name
        	vo["age"] = data["Age"];		
        	vo["country"] = data["Country"];		
        	vo["address"] = data["Address"];		
        	vo["married"] = ( data["Married"] == false ) ? "0" : "1";		
        	
        	return vo;
        	
        },
        
        toVoData2 : function( data ) {
        	var vo  = {};
        	
        	vo["no"] = data["No"];
        	vo["name"] = data["Name"];		//vo.name
        	vo["age"] = data["Age"];		
        	vo["country"] = data["Country"];		
        	vo["address"] = data["Address"];		
        	vo["married"] = ( data["Married"] == false ) ? "0" : "1";		
        	
        	return vo;
        	
        }

    };

    window.db = db;


    db.countries = [
        { Name: "", Id: 0 },
        { Name: "United States", Id: 1 },
        { Name: "Canada", Id: 2 },
        { Name: "United Kingdom", Id: 3 },
        { Name: "France", Id: 4 },
        { Name: "Brazil", Id: 5 },
        { Name: "China", Id: 6 },
        { Name: "Russia", Id: 7 }
    ];
    
    db.clients = [];

    db.users = [];

}());