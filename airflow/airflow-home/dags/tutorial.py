from datetime import datetime, timedelta
from textwrap import dedent
from insert_in_database import insert_image
from insert_in_database import insert_mediaprepare
from insert_in_database import insert_printcycle
from read_file_ids import read_file_ids
from clean_data import clean_image_data
from clean_data import clean_mediaprepare_data
from clean_data import clean_printcycle_data
from delete_temp import delete_temp
from notify_backend import notify_backend

# The DAG object; we'll need this to instantiate a DAG
from airflow import DAG
from airflow.models.baseoperator import chain

# Operators; we need this to operate!
from airflow.operators.bash import BashOperator
from airflow.operators.python import PythonOperator
# These args will get passed on to each operator
# You can override them on a per-task basis during operator initialization
default_args = {
    'owner': 'airflow',
    'depends_on_past': False,
    'email': ['airflow@example.com'],
    'email_on_failure': False,
    'email_on_retry': False,
    'retries': 1,
    'retry_delay': timedelta(minutes=5),
    # 'queue': 'bash_queue',
    # 'pool': 'backfill',
    # 'priority_weight': 10,
    # 'end_date': datetime(2016, 1, 1),
    # 'wait_for_downstream': False,
    # 'dag': dag,
    # 'sla': timedelta(hours=2),
    # 'execution_timeout': timedelta(seconds=300),
    # 'on_failure_callback': some_function,
    # 'on_success_callback': some_other_function,
    # 'on_retry_callback': another_function,
    # 'sla_miss_callback': yet_another_function,
    # 'trigger_rule': 'all_success'
}
with DAG(
    'tutorial',
    default_args=default_args,
    description='A simple tutorial DAG',
    start_date=datetime(2021, 12, 29),
    catchup=False,
    tags=['example'],
    schedule_interval='@daily',
) as dag:

    # t1, t2 and t3 are examples of tasks created by instantiating operators
    t1 = PythonOperator(
        task_id='read_file_ids',
        python_callable=read_file_ids,
        dag=dag,
    )

    t21 = PythonOperator(
        task_id='clean_image_data',
        python_callable=clean_image_data,
        dag=dag,
    )

    t22 = PythonOperator(
        task_id='clean_printcycle_data',
        python_callable=clean_printcycle_data,
        dag=dag,
    )

    t23 = PythonOperator(
        task_id='clean_mediaprepare_data',
        python_callable=clean_mediaprepare_data,
        dag=dag,
    )

    t31 = PythonOperator(
        task_id = 'insert_image',
        python_callable=insert_image,
        dag=dag
    )

    t32 = PythonOperator(
        task_id = 'insert_printcycle',
        python_callable = insert_printcycle,
        dag=dag
    )

    t33 = PythonOperator(
        task_id = 'insert_mediaprepare',
        python_callable = insert_mediaprepare,
        dag=dag
    )

    t4 = PythonOperator(
        task_id='delete_temp',
        python_callable=delete_temp,
        dag=dag,
    )

    t5 = PythonOperator(
        task_id='notify_backend',
        python_callable=notify_backend,
        dag=dag,
    )

    #t99 = PythonOperator(
    #    task_id='insert_in_database',
    #    python_callable=clean_image_data,
    #    dag=dag,
    #)

    t1.doc_md = dedent(
        """\
    #### Task Documentation
    You can document your task using the attributes `doc_md` (markdown),
    `doc` (plain text), `doc_rst`, `doc_json`, `doc_yaml` which gets
    rendered in the UI's Task Instance Details page.
    ![img](http://montcs.bloomu.edu/~bobmon/Semesters/2012-01/491/import%20soul.png)

    """
    )

    dag.doc_md = __doc__  # providing that you have a docstring at the beginning of the DAG
    dag.doc_md = """
    This is a documentation placed anywhere
    """ 

    chain(t1, [t21, t22, t23], [t31, t32, t33], t4, t5)