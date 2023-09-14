
$(function() {
    
    $("#submit_task").click(createTask);
    // Apenas para alimentar alguns dados na lista -----------
    deleteToAll();
    editToAll();
    // ---------------------------------------------------------
   
   
    function deleteToAll(){
        $(".delete_task").each(function() {
            $(this).unbind('click')            
            $(this).click(()=>{
                deleteTask($(this).closest("article"));                
            })
        })
    } 

    function editToAll(){
        $(".edit_task").each(function() {
            $(this).unbind('click')            
            $(this).click(()=>{
                editTask($(this).closest("article"));              
            })
        })
    }   
    
    function deleteTask(task){
        console.log(task);
        task.remove()
    }

    function createTask() {
        let name = $("#new_task_name").val();
        let due = $("#new_task_due").val();
        let category = $("#new_task_category").val();
        let priority = $("#new_task_priority").val();
        let description = $("#new_task_description").val();
        
        try{
            if(name && due && category && priority && description){
                checkExistentTask(name);
                taskBuild(name, due, category,priority,description);            
            } else{
                alert("Please complete all fields");
            }   
        } catch(error) {
            alert(error);
        }               
    }

    function checkExistentTask(name) {
        
        $(".task").each(function() {
            if($(this).attr('id') == name){                
                deleteTask(this);
            }
        })   
        
    }

    function taskBuild(name, due, category, priority, description){

        let date = new Date(due);

        date = parseDate(date,2)
        
        let new_task = `<article class="task" id="${name}">
                            <div class="task_main">
                                <h3>${name}</h3>
                                <p>${description}</p>
                            </div>                            
                            <div class="task_info">
                                <div class="task_details">
                                    <p>${date}</p>                          <p>${category}</p>
                                    <p>${priority}</p>
                                </div>
                                <div class="task_actions">
                                    <button class="edit_task" id="edit_${name}">
                                        <img src="./assets/pencil.png">
                                    </button>
                                    <button class="delete_task" id="delete_${name}">
                                        <img src="./assets/bin.png">
                                    </button>
                                </div>
                            </div>
                        </article>`


        $("#task_list").append(new_task)
        // addDeleteToButton("delete_"+ name);
        deleteToAll();
        editToAll();
    }
    
    function editTask(task){

        let children = $(task).children().children()

        console.log(children);

        $("#new_task_name").val(children[0].innerText);        
        $("#new_task_description").val(children[1].innerText);

        let newDue = children[2].children[0].innerText;
        // 01/15/1999
        newDue = newDue.split('/');
        newDue = new Date(newDue[2]+"-"+newDue[1]+"-"+newDue[0]);
        
        
        console.log(newDue);
        $("#new_task_due").val(parseDate(newDue,1));


        $("#new_task_category").val(children[2].children[1].innerText);
        $("#new_task_priority").val(children[2].children[2].innerText);
        
    }

    function parseDate(date,type) {
        let day = date.getUTCDate() < 10 ? 
                    "0"+date.getUTCDate() : 
                    date.getUTCDate();

        let month = date.getUTCMonth()+1 < 10 ?
                    "0"+ (date.getUTCMonth()+1) : 
                    date.getUTCMonth()+1

        let year = date.getUTCFullYear();

        return type == 1 ? `${year}-${month}-${day}` : `${day}/${month}/${year}`
    }

})