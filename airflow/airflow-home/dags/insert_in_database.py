import os
import mysql.connector
import pandas as pd
from sqlalchemy import create_engine

def insert_image():
    engine = create_engine("mysql://dbi449841:12345678@studmysql01.fhict.local/dbi449841")

    print("image file")
    for subdir, dirs, files in os.walk("/opt/temp"):
        for file in files:
            if (file.startswith('Image')):
                print(os.path.join(subdir, file))
                df = pd.read_csv("/opt/temp/cleaned_" + file, compression='gzip')
                print(df.machine_id.to_string(index=False))
                df = df.loc[:, ~df.columns.str.contains('^Unnamed')]
                df.to_sql('tempimagetable', con=engine, if_exists='replace', index=False)

                connection = engine.connect()
                result = connection.execute("INSERT IGNORE INTO image " +
                "(ull_id, date, time, local_time, engine_cycle_id, image_id, image_result, image_length_announced, image_length, image_width, media_type, print_mode, destination, jetted_ink_cyan, " + 
                "jetted_ink_magenta, jetted_ink_yellow, jetted_ink_black, accounted_ink_cyan, accounted_ink_magenta, accounted_ink_yellow, accounted_ink_black, paper_step_marker, machine_id) " +
                "SELECT ull_id, date, time, local_time, engine_cycle_id, image_id, image_result, image_length_announced, image_length, image_width, media_type, print_mode, destination, jetted_ink_cyan, " + 
                "jetted_ink_magenta, jetted_ink_yellow, jetted_ink_black, accounted_ink_cyan, accounted_ink_magenta, accounted_ink_yellow, accounted_ink_black, paper_step_marker, machine_id " + 
                "FROM tempimagetable")
                connection.close()

def insert_printcycle():
    engine = create_engine("mysql://dbi449841:12345678@studmysql01.fhict.local/dbi449841")

    print("print file")
    for subdir, dirs, files in os.walk("/opt/temp"):
        for file in files:
            if (file.startswith('PrintCycle')):
                df = pd.read_csv("/opt/temp/cleaned_" + file, compression='gzip')
                df = df.loc[:, ~df.columns.str.contains('^Unnamed')]
                df.to_sql('tempprintcycletable', con=engine, if_exists='replace', index=False)

                connection = engine.connect()
                result = connection.execute("INSERT IGNORE INTO print_cycle " + 
                "(ull_id, date, time, local_time, engince_cycle_id, print_cycle_id, action_id, result, print_mode, images, square_decimeter, machine_id) " + 
                "SELECT ull_id, date, time, local_time, engince_cycle_id, print_cycle_id, action_id, result, print_mode, images, square_decimeter, machine_id FROM tempprintcycletable")
                connection.close()

def insert_mediaprepare():
    engine = create_engine("mysql://dbi449841:12345678@studmysql01.fhict.local/dbi449841")

    print("media file")
    for subdir, dirs, files in os.walk("/opt/temp"):
        for file in files:

            if (file.startswith('MediaPrepare')):
                df = pd.read_csv("/opt/temp/cleaned_" + file, compression='gzip')
                df = df.loc[:, ~df.columns.str.contains('^Unnamed')]
                df.to_sql('tempmediapreparetable', con=engine, if_exists='replace', index=False)

                connection = engine.connect()
                result = connection.execute("INSERT IGNORE INTO media_prepare " + 
                "(ull_id, date, time, local_time, engine_cycle_id, media_prepare_id, purpose, category, media_type_display_name, media_id, nominal_width, measured, side_edge_left, " +
                "side_edge_right, waiting_for_cleancut, manual_cut, manual_loading, media_thickness, automatic_media_jogging, advance_correction, moist_correction, " +
                "pre_cure_power, post_cure_power, buffer_heater_setpoint, carriage_height_index, " +
                "control_paper_ster_correction, control_paper_step_pattern_type, fan_friction_correction_table, fan_frictionxcoord_table, paper_step_correction_factor, " + 
                "printer_platen_temperature, vacuum_surface_settings, print_strategy, robust_backlit, media_minimal_swath_duration, winding_type, roll_position, prepare_from_drawer, machine_id) " +
                "SELECT " + 
                "ull_id, date, time, local_time, engine_cycle_id, media_prepare_id, purpose, category, media_type_display_name, media_id, nominal_width, measured, side_edge_left, " +
                "side_edge_right, waiting_for_cleancut, manual_cut, manual_loading, media_thickness, automatic_media_jogging, advance_correction, moist_correction, " +
                "pre_cure_power, post_cure_power, buffer_heater_setpoint, carriage_height_index, " +
                "control_paper_ster_correction, control_paper_step_pattern_type, fan_friction_correction_table, fan_frictionxcoord_table, paper_step_correction_factor, " + 
                "printer_platen_temperature, vacuum_surface_settings, print_strategy, robust_backlit, media_minimal_swath_duration, winding_type, roll_position, prepare_from_drawer, machine_id " +
                "FROM tempmediapreparetable")
                connection.close()